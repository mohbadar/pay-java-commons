package uk.gov.pay.commons.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class WrappedStringValueTest {

    @Test
    @SuppressWarnings("SimplifiableJUnitAssertion")
    public void twoObjectsWithSameRuntimeTypeAndSameStringValueAreEqual() {
        var a = ConcreteWrappedStringValue.valueOf("foo");
        var b = ConcreteWrappedStringValue.valueOf("foo");

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    @SuppressWarnings("SimplifiableJUnitAssertion")
    public void twoObjectsWithSameRuntimeTypeAndDifferentStringValuesAreNotEqual() {
        var a = ConcreteWrappedStringValue.valueOf("foo");
        var b = ConcreteWrappedStringValue.valueOf("bar");

        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    @Test
    @SuppressWarnings({"EqualsBetweenInconvertibleTypes", "SimplifiableJUnitAssertion"})
    public void twoObjectsWithDifferentRuntimeTypesAndSameStringValueAreNotEqual() {
        var a = ConcreteWrappedStringValue.valueOf("foo");
        var b = DifferentConcreteWrappedStringValueType.valueOf("foo");

        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    @Test
    @SuppressWarnings({"EqualsBetweenInconvertibleTypes", "SimplifiableJUnitAssertion"})
    public void twoObjectsWithDifferentRuntimeTypesAndDifferentStringValuesAreNotEqual() {
        var a = ConcreteWrappedStringValue.valueOf("foo");
        var b = DifferentConcreteWrappedStringValueType.valueOf("bar");

        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    @Test
    @SuppressWarnings({"ConstantConditions", "EqualsBetweenInconvertibleTypes", "SimplifiableJUnitAssertion"})
    public void notEqualToObjectOfCompletelyDifferentType() {
        var a = ConcreteWrappedStringValue.valueOf("foo");
        var b = "foo";

        assertFalse(a.equals(b));
    }

    @Test
    @SuppressWarnings({"ConstantConditions", "SimplifiableJUnitAssertion"})
    public void notEqualToNull() {
        var a = ConcreteWrappedStringValue.valueOf("foo");

        assertFalse(a.equals(null));
    }

    @Test
    public void twoEqualObjectsHaveSameHashCode() {
        var a = ConcreteWrappedStringValue.valueOf("foo");
        var b = ConcreteWrappedStringValue.valueOf("foo");
        
        assertThat(a.hashCode(), is(b.hashCode()));
    }

    @Test
    public void toStringReturnsWrappedStringValue() {
        var a = ConcreteWrappedStringValue.valueOf("foo");

        assertThat(a.toString(), is("foo"));
    }

    @Test(expected = NullPointerException.class)
    public void cannotInstantiateWithNullString() {
        ConcreteWrappedStringValue.valueOf(null);
    }

    private static class ConcreteWrappedStringValue extends WrappedStringValue {

        private ConcreteWrappedStringValue(String value) {
            super(value);
        }

        static ConcreteWrappedStringValue valueOf(String value) {
            return new ConcreteWrappedStringValue(value);
        }

    }

    private static class DifferentConcreteWrappedStringValueType extends WrappedStringValue {

        private DifferentConcreteWrappedStringValueType(String value) {
            super(value);
        }

        static DifferentConcreteWrappedStringValueType valueOf(String value) {
            return new DifferentConcreteWrappedStringValueType(value);
        }

    }

}
