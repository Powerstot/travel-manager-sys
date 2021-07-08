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
public class Place {
    private String id;
    private String name;
    private String picPath;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date hotTime;
    private Double hotTicket;
    private Double dimTicket;
    private String placeDes;
    private String provinceId;
}
