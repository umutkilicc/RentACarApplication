package com.RentACar.RentACar.dto;

import com.RentACar.RentACar.common.dto.BaseDto;
import com.RentACar.RentACar.consts.MessageConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto extends BaseDto {

    /**
     * Car category information
     */
    @NotBlank(message = MessageConstants.MESSAGE_NOT_BLANK)
    private String category;

    /**
     * Car title information
     */
    @NotBlank(message = MessageConstants.MESSAGE_NOT_BLANK)
    @Size(min = 5, max = 30)
    private String title;

    /**
     * Car description information
     */
    @NotBlank(message = MessageConstants.MESSAGE_NOT_BLANK)
    @Size(min = 4, max = 500)
    private String description;

    /**
     * Car price information
     */
    @NotNull(message = MessageConstants.MESSAGE_NOT_BLANK)
    @Min(50)
    @Max(1000)
    private int price;

    /**
     * List of Cars relation between comments
     */
    private Set<CommentDto> comments;
}
