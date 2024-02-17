package com.consultadd.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
public class Retry {

    public static <T> Optional<T> retry(int times, Supplier<T> supplier) {
        for (int t = 0; t <= times; t++) {
            try {
                return Optional.of(supplier.get());
            } catch (NullPointerException npe) {
                log.error("No Data found");
                break;
            } catch (Exception ex) {
                log.error(ex.getMessage());
                if (t == times) {
                    return Optional.empty();
                }
            }
        }
        return Optional.empty();
    }
}
