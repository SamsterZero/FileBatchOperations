package in.vvm.FileBatchOperations.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;



@RequiredArgsConstructor
@Controller
public class UploadController {

	private final JobLauncher jobLauncher;
	private final Job job;

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletResponse response,Model model) {

		File fileToImport = new File(file.getOriginalFilename());
		response.setHeader("hx-response", "v1");
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("filePath",fileToImport.getAbsolutePath())
				.addLong("startAt",System.currentTimeMillis())
				.toJobParameters();
        try {
			jobLauncher.run(job,jobParameters);
			model.addAttribute("fileName",file.getOriginalFilename());
			return "x";
        }catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            return "Batch Failed";
        }
    }
}