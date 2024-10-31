package com.sduonline.webdesign.data.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    public String session_key;
    public String errmsg;
    public String openid;
    public String errcode;
}
