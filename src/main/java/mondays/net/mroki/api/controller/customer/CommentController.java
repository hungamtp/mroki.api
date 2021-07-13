package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CommentConverter;
import mondays.net.mroki.api.dto.CommentAddDTO;
import mondays.net.mroki.api.dto.CommentDTO;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.exception.CommentConvertException;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user/comment")
@AllArgsConstructor
@CrossOrigin
public class CommentController {

    @Autowired
    private final CommentServiceImpl commentService;

    @Autowired
    private final CommentConverter converter;

    @PostMapping
    public ResponseEntity<ResponseDTO> comment(@Valid @RequestBody CommentAddDTO commentAddDTO) {

        ResponseDTO response = new ResponseDTO();

        try {
            commentService.comment(converter.dtoToEntity(commentAddDTO));
            response.setSuccessCode(SuccessCode.COMMENT_SUCCESS);

            return ResponseEntity.ok().body(response);
        } catch (CommentConvertException ex) {
            response.setErrorCode(ErrorCode.COMMENT_FAILED);

            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<CommentDTO>> getCommentByProductId(@PathVariable Long productId,
                                                                  @RequestParam(required = false) Optional<Integer> page) {

        return ResponseEntity.ok().body(commentService.getComment(page.orElse(0), productId));

    }

}
