package com.javedrpi.xiaourl.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class GlobalError {
    private String errMsg;
    private String refId;
}
