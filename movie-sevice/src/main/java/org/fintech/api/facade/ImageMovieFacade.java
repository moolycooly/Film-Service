package org.fintech.api.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fintech.api.model.UpdateMovieRequest;
import org.fintech.core.model.FileType;
import org.fintech.core.service.FileStorageService;
import org.fintech.core.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageMovieFacade {

    private final FileStorageService fileStorageService;

    private final MovieService movieService;

    public void saveMovieImage(MultipartFile file, String field, long id) {

        String url;

        try{
            url = fileStorageService.uploadFile(file, FileType.MOVIE_PHOTO);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить фото");
        }

        UpdateMovieRequest movie = new UpdateMovieRequest();

        if (field.equals("backdropPath")) {
            movie.setBackdropPath(url);
        }
        else if(field.equals("posterPath")) {
            movie.setPosterPath(url);
        }

        movieService.updateMovie(movie,id);

    }

}
