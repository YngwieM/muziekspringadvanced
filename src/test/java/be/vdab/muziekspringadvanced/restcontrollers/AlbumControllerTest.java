package be.vdab.muziekspringadvanced.restcontrollers;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/insertArtiest.sql")
@Sql("/insertAlbum.sql")
class AlbumControllerTest extends AbstractTransactionalJUnit4SpringContextTests
{
    private final MockMvc mvc;

    public AlbumControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    private long idVanTestAlbum() {
        return super.jdbcTemplate.queryForObject(
                "select id from albums where naam='test'", Long.class);
    }
    @Test
    void onbestaandAlbumLezen() throws Exception {
        mvc.perform(get("/albums/{id}", -1))
                .andExpect(status().isNotFound());
    }
    @Test
    void albumLezen() throws Exception {
        var id = idVanTestAlbum();
        mvc.perform(get("/albums/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("album").value("test"));
    }
}