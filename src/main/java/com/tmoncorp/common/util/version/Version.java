package com.tmoncorp.common.util.version;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Version implements Serializable, Comparable<Version> {
    private static final long serialVersionUID = 12321657321387L;

    private String version;
    private List<Integer> numbers = new ArrayList<>();
    private boolean valid = true;

    public Version(String version) {
        this.version = version;

        if (StringUtils.isEmpty(version)) {
            this.valid = false;
            return;
        }

        String[] numberStrings = StringUtils.split(version, '.');
        for (String numberString : numberStrings) {
            try {
                this.numbers.add(Integer.parseInt(numberString));
            } catch (NumberFormatException e) {
                this.valid = false;
                break;
            }
        }
    }

    public boolean isLaterThan(Version o) {
        return compareTo(o) > 0;
    }

    public boolean isEarlierThan(Version o) {
        return compareTo(o) < 0;
    }

    public boolean isLaterOrEqual(Version o) {
        return isEarlierThan(o) == false;
    }

    public boolean isEarlierOrEqual(Version o) {
        return isLaterThan(o) == false;
    }


    public List<Integer> getNumbers() {
        return numbers;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public int compareTo(Version o) {
        if (o == null) {
            return 1;
        }
        if (this.isValid() == false || o.isValid() == false) {
            return this.isValid()
                    ? 1
                    : o.isValid() ? -1 : 0;
        }

        for (int i = 0; i < Math.min(this.getNumbers().size(), o.getNumbers().size()); i++) {
            if (this.getNumbers().get(i) != o.getNumbers().get(i)) {
                return this.getNumbers().get(i) - o.getNumbers().get(i);
            }
        }
        return this.getNumbers().size() - o.getNumbers().size();
    }

    @Override
    public String toString() {
        return this.valid ? version : ""; //Key for Cache
    }
}
