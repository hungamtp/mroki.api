package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CommentConverter;
import mondays.net.mroki.api.dto.comment.CommentAddDTO;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.comment.CommentTotalDTO;
import mondays.net.mroki.api.exception.CommentConvertException;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("user/comment")
@AllArgsConstructor
@CrossOrigin
public class CommentController {

    private final int PAGE_SIZE = 5;

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
    public ResponseEntity<ResponseDTO> getCommentByProductId(@PathVariable Long productId,
                                                             @RequestParam(required = false) Optional<Integer> page) {
        ResponseDTO response = new ResponseDTO();

        try{
            Pageable pageable = PageRequest.of(0 ,PAGE_SIZE );
            response.setData(converter.entityToDto(commentService.getComment(pageable,productId)));
            response.setSuccessCode(SuccessCode.GET_ALL_COMMENT);

            return ResponseEntity.ok().body(response);
        }catch (CommentConvertException ex){
            response.setErrorCode(ErrorCode.GET_ALL_COMMENT);
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/total/{productId}")
    public CommentTotalDTO getTotalComment(@PathVariable Long productId){

        return commentService.getTotalComment(productId);
    }

}
