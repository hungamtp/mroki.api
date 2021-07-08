package mondays.net.mroki.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveBox {

    @Id
    private Long id;

    private boolean isAvailable;

    @OneToOne(mappedBy = "archiveBox")
    private Orders orders;

    public ArchiveBox(Long id) {
        this.id = id;
    }


}
