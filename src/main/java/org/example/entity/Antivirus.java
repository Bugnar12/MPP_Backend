package org.example.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Antivirus {
    private int id;
    private String name;
    private String producer;
    private String description;
    private boolean supportMultiPlatform;
    private SimpleDateFormat releaseDate;
}
