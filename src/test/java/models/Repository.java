package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository extends BaseModel {

	@JsonProperty("private")
	private boolean isPrivate;
	@JsonProperty("is_template")
	private boolean isTemplate;
	private String name;
	private String description;
	private String homepage;
	@JsonProperty("full_name")
	private String fullName;

}