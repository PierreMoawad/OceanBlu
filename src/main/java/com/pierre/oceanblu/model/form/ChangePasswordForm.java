package com.pierre.oceanblu.model.form;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChangePasswordForm {

    private Long userId;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordForm(Long userId) {

        this.userId = userId;
    }
}
