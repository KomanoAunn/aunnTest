package com.example.demo.model;

import com.example.demo.model.base.BaseModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class User extends BaseModel implements Serializable {
    private String nickname;
    private String cellphone;
    private String password;
    private String salt;

    @Override
    public String toString() {
        return new StringBuilder(this.getClass().getSimpleName()).append("(")
                .append("id=").append(this.getId()).append(",")
                .append("nickname=").append(this.getNickname()).append(",")
                .append("cellphone=").append(this.getCellphone()).append(",")
                .append("salt=").append(this.salt)
                .append(")").toString();
    }
}
