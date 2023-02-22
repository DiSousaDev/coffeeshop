package br.dev.diego.coffeeshop.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface GeneralUtilities {

    static URI toUri(String path, Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path(path).buildAndExpand(id).toUri();
    }

}
