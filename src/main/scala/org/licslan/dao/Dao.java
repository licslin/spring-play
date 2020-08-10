package org.licslan.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Repository;

@Repository
@Data
@ToString
@NoArgsConstructor
public class Dao {
    private String name="licslan";
    private int age=10;
}
