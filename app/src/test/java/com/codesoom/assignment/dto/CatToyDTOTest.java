package com.codesoom.assignment.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CatToyDTO 클래스")
class CatToyDTOTest {
    private final String givenName = "cat nip";
    private final String givenMaker = "dog";
    private final double givenPrice = 1000;
    private final String givenImageUrl = "https://cat.toy/cat-nip.png";
    private final CatToyDTO givenCatToyDTO = new CatToyDTO.builder()
            .name(givenName)
            .maker(givenMaker)
            .price(givenPrice)
            .imageUrl(givenImageUrl)
            .build();
    private final String givenCatToyDTOJson = String.format(
            "{\"name\":\"%s\",\"maker\":\"%s\",\"price\":%.1f,\"imageUrl\":\"%s\"}",
            givenName,
            givenMaker,
            givenPrice,
            givenImageUrl
    );

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void name() {
        assertThat(givenCatToyDTO.name()).isEqualTo(givenName);
    }

    @Test
    void maker() {
        assertThat(givenCatToyDTO.maker()).isEqualTo(givenMaker);
    }

    @Test
    void price() {
        assertThat(givenCatToyDTO.price()).isEqualTo(givenPrice);
    }

    @Test
    void imageUrl() {
        assertThat(givenCatToyDTO.imageUrl()).isEqualTo(givenImageUrl);
    }

    @Test
    void stringToJson() throws JsonProcessingException {
        CatToyDTO catToyDTO = objectMapper.readValue(givenCatToyDTOJson, CatToyDTO.class);

        assertThat(catToyDTO).isEqualTo(new CatToyDTO(givenName, givenMaker, givenPrice, givenImageUrl));
    }

    @Test
    void toStringTest() throws JsonProcessingException {
        assertThat(objectMapper.writeValueAsString(givenCatToyDTO)).isEqualTo(givenCatToyDTOJson);
    }

    @Nested
    @DisplayName("equals 메서드는")
    class Describe_equals {
        @Nested
        @DisplayName("비교 대상이 CatToyDTO 클래스가 아닐 때")
        class Context_when_compare_is_not_CatToyDTO {
            private final String givenCompare = "compare target";

            @Test
            @DisplayName("false 를 리턴한다.")
            void It_returns_false() {
                assertThat(givenCatToyDTO).isNotEqualTo(givenCompare);
            }
        }

        @Nested
        @DisplayName("비교 대상과 name 이 다를 때")
        class Context_when_compare_is_not_equals_name {
            private final CatToyDTO givenCompare = new CatToyDTO.builder()
                    .name("compare target")
                    .maker(givenMaker)
                    .price(givenPrice)
                    .imageUrl(givenImageUrl)
                    .build();

            @Test
            @DisplayName("false 를 리턴한다.")
            void It_returns_false() {
                assertThat(givenCatToyDTO).isNotEqualTo(givenCompare);
            }
        }

        @Nested
        @DisplayName("비교 대상과 maker 가 다를 때")
        class Context_when_compare_is_not_equals_maker {
            private final CatToyDTO givenCompare = new CatToyDTO.builder()
                    .name(givenName)
                    .maker("compare target")
                    .price(givenPrice)
                    .imageUrl(givenImageUrl)
                    .build();

            @Test
            @DisplayName("false 를 리턴한다.")
            void It_returns_false() {
                assertThat(givenCatToyDTO).isNotEqualTo(givenCompare);
            }
        }

        @Nested
        @DisplayName("비교 대상과 price 가 다를 때")
        class Context_when_compare_is_not_equals_price {
            private final CatToyDTO givenCompare = new CatToyDTO.builder()
                    .name(givenName)
                    .maker(givenMaker)
                    .price(2000d)
                    .imageUrl(givenImageUrl)
                    .build();

            @Test
            @DisplayName("false 를 리턴한다.")
            void It_returns_false() {
                assertThat(givenCatToyDTO).isNotEqualTo(givenCompare);
            }
        }

        @Nested
        @DisplayName("비교 대상과 imageUrl 이 다를 때")
        class Context_when_compare_is_not_equals_imageUrl {
            private final CatToyDTO givenCompare = new CatToyDTO.builder()
                    .name(givenName)
                    .maker(givenMaker)
                    .price(givenPrice)
                    .imageUrl("compare target")
                    .build();

            @Test
            @DisplayName("false 를 리턴한다.")
            void It_returns_false() {
                assertThat(givenCatToyDTO).isNotEqualTo(givenCompare);
            }
        }
    }

    @Test
    void buildToString() {
        assertThat(new CatToyDTO.builder().toString()).isEqualTo("CatToyDTO.builder(name=null, maker=null, price=null, imageUrl=null)");
    }
}
