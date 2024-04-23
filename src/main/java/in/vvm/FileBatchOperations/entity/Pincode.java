package in.vvm.FileBatchOperations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "Pincode")
@Data
@Builder
@AllArgsConstructor
public class Pincode {

	@Id
	private Long pincode;
	private String state;
	private String district;
	private String city;

}