package com.maths.app.core.either;

public final class Right<L, R> implements Either<L, R> {
    private final R value;

    public Right(R value) {
        this.value = value;
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public boolean isRight() {
        return true;
    }

    @Override
    public L getLeftValue() {
        throw new UnsupportedOperationException("Cannot get left value from Right instance.");
    }

    @Override
    public R getRightValue() {
        return value;
    }
}