import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;


@RunWith(JUnit4.class)
public class TestRTriangle {

    public static BigInteger getLengthOfVector(int x1, int y1, int x2, int y2) {
        BigInteger b1, b2;
        b1 = new BigInteger(Integer.toString(x1));
        BigInteger minusOne = new BigInteger("-1");
        b1 = b1.add(new BigInteger(Integer.toString(x2)).multiply(minusOne));

        b2 = new BigInteger(Integer.toString(y1));
        b2 = b2.add(new BigInteger(Integer.toString(y2)).multiply(minusOne));

        b1 = b1.multiply(b1);
        b2 = b2.multiply(b2);

        return b1.add(b2);
        //return Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2);
    }

    public static BigInteger getLengthOfHypotenuse(String a, String b) {
        BigInteger b1, b2;
        b1 = new BigInteger(a);
        b2 = new BigInteger(b);

        b1 = b1.multiply(b1);
        b2 = b1.multiply(b2);

        return b1.add(b2);
//        return Math.sqrt(Math.pow((a), 2) + Math.pow((b), 2));
    }


    public static boolean hasOrthogonality(BigInteger[] a, BigInteger[] b) {
        a[0] = a[0].multiply(b[0]);
        a[1] = a[1].multiply(b[1]);
        return a[0].add(a[1]).toString().equals("0");
        //return a[0] * b[0] + a[1] * b[1] == 0;
    }

    public static BigInteger[] getVector(int x1, int y1, int x2, int y2) {
        BigInteger b1 = new BigInteger(Integer.toString(x1));
        BigInteger minusOne = new BigInteger("-1");
        b1 = b1.add(new BigInteger(Integer.toString(x2)).multiply(minusOne));

        BigInteger b2 = new BigInteger(Integer.toString(y1));
        b2 = b2.add(new BigInteger(Integer.toString(y2)).multiply(minusOne));

        return new BigInteger[] {b1, b2};
            // retunr new int[] {x1 - x2, y1 - y2 };
        }



    @Test
    public void testPointsNotEquals() {
        Rtriangle rtriangle = RtriangleProvider.getRtriangle();
        Assert.assertNotNull(rtriangle);

        Assert.assertTrue(rtriangle.getApexX1() != rtriangle.getApexX2()
                || rtriangle.getApexY1() != rtriangle.getApexY2());
        Assert.assertTrue(rtriangle.getApexX2() != rtriangle.getApexX3()
                || rtriangle.getApexY2() != rtriangle.getApexY3());
        Assert.assertTrue(rtriangle.getApexX3() != rtriangle.getApexX1()
                || rtriangle.getApexY3() != rtriangle.getApexY1());
    }

    @Test
    public void testPropertyOfHypotenuse() {
        Rtriangle rtriangle = RtriangleProvider.getRtriangle();
        Assert.assertNotNull(rtriangle);

        ArrayList<BigInteger> list = new ArrayList<>();

        list.add(getLengthOfVector(rtriangle.getApexX1(), rtriangle.getApexY1(), rtriangle.getApexX2(), rtriangle.getApexY2()));
        list.add(getLengthOfVector(rtriangle.getApexX2(), rtriangle.getApexY2(), rtriangle.getApexX3(), rtriangle.getApexY3()));
        list.add(getLengthOfVector(rtriangle.getApexX3(), rtriangle.getApexY3(), rtriangle.getApexX1(), rtriangle.getApexY1()));

        Collections.sort(list);

        Assert.assertNotEquals(list.get(list.size() - 2), list.get(list.size() - 1));
    }

    @Test
    public void testPythagoreanTheorem() {
        Rtriangle rtriangle = RtriangleProvider.getRtriangle();
        Assert.assertNotNull(rtriangle);

        ArrayList<BigInteger> list = new ArrayList<>();

        list.add(getLengthOfVector(rtriangle.getApexX1(), rtriangle.getApexY1(), rtriangle.getApexX2(), rtriangle.getApexY2()));
        list.add(getLengthOfVector(rtriangle.getApexX2(), rtriangle.getApexY2(), rtriangle.getApexX3(), rtriangle.getApexY3()));
        list.add(getLengthOfVector(rtriangle.getApexX3(), rtriangle.getApexY3(), rtriangle.getApexX1(), rtriangle.getApexY1()));

        Collections.sort(list);

        BigInteger hypotenuse = list.get(list.size() - 1);

        Assert.assertEquals(hypotenuse, list.get(0).add(list.get(1)));
    }

    @Test
    public void testOrthogonality() {
        Rtriangle rtriangle = RtriangleProvider.getRtriangle();
        Assert.assertNotNull(rtriangle);
        int x1 = rtriangle.getApexX1(),
                y1 = rtriangle.getApexY1(),
                x2 = rtriangle.getApexX2(),
                y2 = rtriangle.getApexY2(),
                x3 = rtriangle.getApexX3(),
                y3 = rtriangle.getApexY3();

        Assert.assertTrue(
                hasOrthogonality(
                        getVector(x1, y1, x2, y2), getVector(x1, y1, x3, y3)
                ) || hasOrthogonality(
                        getVector(x2, y2, x3, y3), getVector(x2, y2, x1, y1)
                ) || hasOrthogonality(
                        getVector(x3, y3, x1, y1), getVector(x3, y3, x2, y2)
                )
        );
    }


}
