package com.github.bingoohuang.ceping.hogan;

import com.github.bingoohuang.utils.net.Url;
import lombok.val;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class HoganApiTest {
    @Test
    public void createAutoLoginLink() {
        val link = new HoganApi().createAutoLoginLink("HF487827", "fR7NeA8MsE!WwMF", null);
        assertThat(Url.ping(link, 2000)).isTrue();
    }
}
