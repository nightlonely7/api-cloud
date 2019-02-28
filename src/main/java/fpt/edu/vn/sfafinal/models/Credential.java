package fpt.edu.vn.sfafinal.models;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Credential {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
