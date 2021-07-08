package com.powerstot.travels.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Monitor {
    private String id;
    private String picPath;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shotTime;
    private boolean isChecked;

}
