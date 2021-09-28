package com.f_lin.future.solution.algorithm.leetcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/11/26 16:30
 **/
public class ZotTest {
    public static void main(String[] args) {

    }


    private static void zot1(int maxIndex) {
        int startX = 0, startY = 0, startZ = 0;


    }




    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class Node implements Cloneable {
        //默认是正方形。如果不是正方形则需要定义max x y z
        private int maxIndex;

        private int currentX;
        private int currentY;
        private int currentZ;
        private int distance;

        @Override
        protected Node clone() throws CloneNotSupportedException {
            Node clone = (Node) super.clone();
            clone.setCurrentX(currentX);
            clone.setCurrentY(currentY);
            clone.setCurrentZ(currentZ);
            clone.setDistance(distance);
            return clone;
        }

        public boolean isArrive() {
            return currentX == maxIndex
                    && currentY == maxIndex
                    && currentZ == maxIndex;
        }
    }
}

