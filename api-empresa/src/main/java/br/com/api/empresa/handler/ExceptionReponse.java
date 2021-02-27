package br.com.api.empresa.handler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionReponse {

	private String message;

	private String details;

	private LocalDateTime timestemp;
}
