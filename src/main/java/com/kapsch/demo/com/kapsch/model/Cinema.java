package com.kapsch.demo.com.kapsch.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
@EqualsAndHashCode(of = "name")
@Getter
@Setter
@ToString(of = "name")
@Table(name = "cinema")
@NamedEntityGraphs({
    @NamedEntityGraph(name = Cinema.MOVIES, attributeNodes = @NamedAttributeNode("movies"))
})
public class Cinema {
    public static final String MOVIES = "Cinema[movies]";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "movie_cinema",
        joinColumns = {@JoinColumn(name = "cinema_id")},
        inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    private List<Movie> movies = new ArrayList<>();
}
