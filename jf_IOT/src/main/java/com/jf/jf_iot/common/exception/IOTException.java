package com.jf.jf_iot.common.exception;

import com.jf.jf_iot.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IOTException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
