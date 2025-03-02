package es.cesguiro.daw1.bookstore.domain.model.vo;

import java.util.Objects;

public class LocaleString {

    private final String es;
    private final String en;

    public LocaleString(String es, String en) {
        this.es = es;
        this.en = en;
    }

    public String getValue(String language) {
        return language.equals("en") ? en : es;
    }

    /*public String getEs() {
        return es;
    }

    public String getEn() {
        return en;
    }*/

    @Override
    public String toString() {
        return "LocaleString{" +
                "es='" + es + '\'' +
                ", en='" + en + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        LocaleString that = (LocaleString) object;
        return Objects.equals(es, that.es) && Objects.equals(en, that.en);
    }

    @Override
    public int hashCode() {
        return Objects.hash(es, en);
    }
}
