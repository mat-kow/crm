package pl.teo.crm.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProjectCreationDto {
    @NotBlank(message = "notBlank") @Size(min = 3, max = 20, message = "size")
    private String name;
    @NotNull @Size(max = 200, message = "size")
    private String description;
    @Pattern(regexp = "((https?:\\/\\/)?[\\w\\d]+([.\\w\\d]+)*(:\\d+)?(\\/[\\w\\d]+)*)|()", message = "pattern")// todo fix regex
    private String site;

}
