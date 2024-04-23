package in.vvm.FileBatchOperations.config;

import in.vvm.FileBatchOperations.entity.Pincode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Log4j2
@Configuration
public class UploadBatchConfig {

    @PersistenceContext
    EntityManager entityManager;

    @Value("#jobParameter[filePath]")
    String path;

    @Bean
    @StepScope
    public PoiItemReader<Pincode> excelReader() {
        PoiItemReader<Pincode> reader = new PoiItemReader<>();
        reader.setResource(new FileSystemResource(path));
        reader.setName("Excel Reader");
        reader.setLinesToSkip(1);
        reader.setRowMapper(excelRowMapper());
        log.info("Reader initialized");
        return reader;
    }

    private BeanWrapperRowMapper<Pincode> excelRowMapper() {
        BeanWrapperRowMapper<Pincode> rowMapper = new BeanWrapperRowMapper<>();
        rowMapper.setTargetType(Pincode.class);
        log.info("Excel Row Mapper initialized");
        return rowMapper;
    }

    @Bean
    public ExcelProcessor dataProcessor() {
        return new ExcelProcessor();
    }

    @Bean
    public JpaItemWriter<Pincode> dbWriter() {
        return new JpaItemWriterBuilder<Pincode>()
                .entityManagerFactory(entityManager.getEntityManagerFactory())
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository) {
        PlatformTransactionManager transactionManager = new ResourcelessTransactionManager();
        return new StepBuilder("Step 1", jobRepository)
                .<Pincode, Pincode>chunk(10, transactionManager)
                .reader(excelReader())
                .processor(dataProcessor())
                .writer(dbWriter())
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, CompletionNotifierListener listener) {
        return new JobBuilder("Excel 2 DB", jobRepository)
                .listener(listener)
                .start(step(jobRepository))
                .build();
    }
}