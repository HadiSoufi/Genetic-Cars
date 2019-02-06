/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jracerevolution;

import javafx.scene.Node;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

/**
 *
 * @author Hadi
 */
public class Car {

    public Node node;
    
    Body body;
    Body wheel, wheel2;

    public Car(int posX, int posY) {
        
    }
    
    private void create(World world, int posx, int posy) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.setType(BodyType.DYNAMIC);
        bodyDef.position.set(posx, posy);
        
        PolygonShape dynamicCircle = new PolygonShape();
        dynamicCircle.setAsBox(8, 3);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicCircle;
        fixtureDef.density = 4.0f;
        fixtureDef.restitution = 0.1f;
        fixtureDef.friction = 0.95f;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.setType(BodyType.DYNAMIC);
        
        CircleShape dynamicCircle2 = new CircleShape();
        dynamicCircle2.setRadius(2);
        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = dynamicCircle2;
        fixtureDef2.density = 4;
        fixtureDef2.friction = 3.0f;
        fixtureDef2.restitution = 0.1f;
        
        wheel = world.createBody(bodyDef2);
        wheel.createFixture(fixtureDef2);

        wheel2 = world.createBody(bodyDef2);
        wheel2.createFixture(fixtureDef2);

        RevoluteJointDef revoluteJointDef = new RevoluteJointDef();
        revoluteJointDef.bodyA = body;
        revoluteJointDef.bodyB = wheel;
        revoluteJointDef.localAnchorA.addLocal(-8, -6);
        world.createJoint(revoluteJointDef);

        RevoluteJointDef revoluteJointDef2 = new RevoluteJointDef();
        revoluteJointDef2.bodyA = body;
        revoluteJointDef2.bodyB = wheel2;
        revoluteJointDef2.localAnchorA.addLocal(8, -6);
        world.createJoint(revoluteJointDef2);

    }

    public Vec2 getPos() {
        return body.getPosition();
    }

    public void update(int x) {
        Vec2 wheelVec = new Vec2(wheel.getPosition().x, wheel.getPosition().y);
        Vec2 wheel2Vec = new Vec2(wheel2.getPosition().x, wheel2.getPosition().y);
        if (x < 600) {
            wheel.setTransform(wheelVec, wheel.getAngle() + 0.1f);
            wheel2.setTransform(wheel2Vec, wheel2.getAngle() + 0.1f);
        } else {
            wheel.setTransform(wheelVec, wheel.getAngle() - 0.1f);
            wheel2.setTransform(wheel2Vec, wheel2.getAngle() - 0.1f);
        }
    }
}
