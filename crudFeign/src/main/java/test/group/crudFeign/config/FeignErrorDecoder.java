package test.group.crudFeign.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import test.group.crudFeign.customExceptions.BadRequestException;
import test.group.crudFeign.customExceptions.InternalServerErrorException;
import test.group.crudFeign.customExceptions.NotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());

        String message = extractMessageFromBody(response);

        return switch (status) {
            case NOT_FOUND -> new NotFoundException(message);
            case BAD_REQUEST -> new BadRequestException(message);
            case INTERNAL_SERVER_ERROR -> new InternalServerErrorException(message);
            default -> new Exception("unexpected error occurred" + message);
        };
    }

    private String extractMessageFromBody(Response response) {
        if (response.body() == null) {
            return "no message available";
        }
        try (InputStream inputStream = response.body().asInputStream()) {
            byte[] bytes = inputStream.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "error with message";
        }
    }
}
