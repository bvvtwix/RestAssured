package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseModel {

	private String login;
	private String url;
	@JsonProperty("private")
	private boolean isPrivate;
	@JsonProperty("is_template")
	private boolean isTemplate;
	@JsonProperty("repos_url")
	private String reposUrl;

}
