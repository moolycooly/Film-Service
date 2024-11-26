package org.fintech.api.controller;

import lombok.RequiredArgsConstructor;
import org.fintech.api.ApiPaths;
import org.fintech.api.facade.ImageMovieFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ImageMovieRestController {

    private final ImageMovieFacade imageMovieFacade;

    @PostMapping(value = ApiPaths.MOVIE_IMAGE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadImage(@RequestParam("file") MultipartFile file,
                            @PathVariable("id") long id,
                            @RequestParam("field") String field
    ) {
        imageMovieFacade.saveMovieImage(file,field,id);
    }
}
