package java0729;

import java.awt.*;

class Obstacle {
    private int x, y, width, height, speed;

    public Obstacle(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public void move() {
        x -= speed;
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }

    public Rectangle getBounds() {
        // y 좌표를 수정하여 장애물의 위치를 맞춥니다
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}