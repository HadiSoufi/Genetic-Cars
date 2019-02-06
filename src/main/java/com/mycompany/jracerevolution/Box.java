/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jracerevolution;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 *
 * @author Hadi
 */
public class Box {

    public Box(World world) {

        //Set the properties of the ball
        BodyDef bodyDef = new BodyDef();
        bodyDef.setType(BodyType.DYNAMIC);
        bodyDef.position.set(0, 20);

        //Create the box object
        Body box = world.createBody(bodyDef);

        //Set the shape
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(1, 1);

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.setShape(boxShape);
        boxFixtureDef.density = 1;
        box.createFixture(boxFixtureDef);
    }
}
