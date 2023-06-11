package com.RentACar.RentACar.dto;

import com.RentACar.RentACar.common.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto extends BaseDto {

    /**
     * Comment information
     */
    private String comment;

    /**
     * Car Id information
     */
    private UUID carId;

    /**
     * Posted By information
     */
    private String postedBy;
}
