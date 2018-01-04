package com.chimpim.toolbox.common;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class CollectionUtils {

    @Nullable
    public static <T> T findItem(@NotNull Collection<T> collection, @NotNull T target) {
        for (T it : collection) {
            if (ObjectUtils.equals(it, target)) {
                return it;
            }
        }
        return null;
    }
}
