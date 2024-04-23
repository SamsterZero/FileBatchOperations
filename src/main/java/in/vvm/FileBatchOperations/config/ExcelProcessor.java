package in.vvm.FileBatchOperations.config;

import in.vvm.FileBatchOperations.entity.Pincode;
import org.springframework.batch.item.ItemProcessor;

public class ExcelProcessor implements ItemProcessor<Pincode, Pincode> {
    @Override
    public Pincode process(Pincode item) throws Exception {
        return item;
    }
}
