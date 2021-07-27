package mondays.net.mroki.api.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArchiveBox {

    @Id
    private Long id;

    private boolean isAvailable;

    @OneToOne(mappedBy = "archiveBox" , fetch = FetchType.LAZY)
    private Orders orders;

}
