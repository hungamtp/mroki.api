package mondays.net.mroki.api.dto.commentDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentTotalDTO {
    float rate;
    int count;
    int countRate1;
    int countRate2;
    int countRate3;
    int countRate4;
    int countRate5;
}
