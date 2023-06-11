package com.RentACar.RentACar.dto;

import com.RentACar.RentACar.common.dto.BaseDto;
import com.RentACar.RentACar.consts.MessageConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto extends BaseDto {

    /**
     * username information in User Table
     */
    @NotBlank(message = MessageConstants.MESSAGE_NOT_BLANK)
    @Size(min = 4, max = 15)
    private String username;

    /**
     * password information in User Table
     */
    @NotBlank(message = MessageConstants.MESSAGE_NOT_BLANK)
    @Size(min = 4, max = 50)
    private String password;

    /**
     * Confirmation password
     */
    @Size(min = 4, max = 50)
    private String cPassword;

    /**
     * Entity Email information
     */
    @Email
    @NotBlank(message = MessageConstants.MESSAGE_NOT_BLANK)
    @Size(min = 10, max = 50)
    private String email;

    /**
     * Entity Phone Number information
     */
    @Size(min = 6, max = 20)
    private String phoneNumber;

    /**
     * User gender information
     */
    @NotBlank(message = MessageConstants.MESSAGE_NOT_BLANK)
    private String gender;

    /**
     * Spent information
     */
    private String spent;

    /**
     * Registration Date information
     */
    private LocalDate createDate;

    /**
     * User enabled information
     */
    private boolean isEnable = true;

    /**
     * Relation of role entity
     */
    private Set<RoleDto> roles;
}
