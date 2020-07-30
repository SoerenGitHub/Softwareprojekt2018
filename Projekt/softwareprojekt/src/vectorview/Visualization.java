/**
    created by Luca König
    MtrNr. 70454415
 */
package vectorview;

import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javax.swing.*;
import java.util.ArrayList;

public class Visualization{
    /**
     * Author: Luca König
     * all needed variables
     */
    //region Variables
    // groups
    private final Group root = new Group();
    private final Xform axisGroup = new Xform();
    private final Xform world = new Xform();
    private Group figureGroup = new Group();
    private Group verticGroup = new Group();
    private Xform xAxisRotate = new Xform();
    private Xform yAxisRotate = new Xform();
    private Xform zAxisRotate = new Xform();
    private Group coneGroup = new Group();

    // selected object
    private Node selected;

    // camera variables
    private final PerspectiveCamera camera = new PerspectiveCamera(true);
    private final Xform cameraXform = new Xform();
    private final Xform cameraXform2 = new Xform();
    private final Xform cameraXform3 = new Xform();

    // initial camera variables
    static final double CAMERA_INITIAL_DISTANCE = -50;
    static final double CAMERA_INITIAL_X_ANGLE = -90;
    static final double CAMERA_INITIAL_Y_ANGLE = 15;
    static final double CAMERA_INITIAL_Z_ANGLE = 105;
    static final double CAMERA_NEAR_CLIP = 0.1;
    static final double CAMERA_FAR_CLIP = 10000.0;


    // control variables
    private static final double CONTROL_MULTIPLIER = 0.1;
    private static final double SHIFT_MULTIPLIER = 10.0;
    private static final double MOUSE_SPEED = 0.1;
    private static final double ROTATION_SPEED = 2.0;
    private static final double TRACK_SPEED = 0.3;

    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;
    private double mouseScroll;

    // axis variables
    private double axis_rad = 0.04;
    private static final double AXIS_LENGTH = 75;
    private int rounds = 360;
    private int r1 = 0;
    private float r2 = (float)axis_rad * 4;
    private float h = (float)0.6;
    //endregion

    /**
     * Author: Luca König
     * Function: builds the camera for the subscene
     * using the XForm class
     */
    private void buildCamera() {
        // creates new camera object
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateY(90);

        // set initial position
        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
        cameraXform.rz.setAngle(CAMERA_INITIAL_Z_ANGLE);
    }

    /**
     * Author: Luca König
     * Function: builds the axes for the visualization
     */
    private void buildAxis() {
        // creating new cylinders
        //region new cylinders
        Cylinder yAxis = new Cylinder(axis_rad, AXIS_LENGTH, 128);
        Cylinder xAxis = new Cylinder(axis_rad, AXIS_LENGTH, 128);
        Cylinder zAxis = new Cylinder(axis_rad, AXIS_LENGTH, 128);
        //endregion
        // creating materials
        //region material
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        // set materials
        yAxis.setMaterial(redMaterial);
        zAxis.setMaterial(greenMaterial);
        xAxis.setMaterial(blueMaterial);
        //endregion

        // creating  cones
        //region cones
        createCone(Color.GREEN);
        coneGroup.getChildren().get(0).setTranslateY(AXIS_LENGTH / 2);
        createCone(Color.RED);
        coneGroup.getChildren().get(1).setTranslateX(AXIS_LENGTH / 2);
        createCone(Color.BLUE);
        coneGroup.getChildren().get(2).setTranslateZ(AXIS_LENGTH / 2);
        //endregion
        // creating cone covers
        //region cover for cone downside
        Cylinder cy = new Cylinder(axis_rad * 4, 0, 128);
        cy.setTranslateY(AXIS_LENGTH / 2 - 0.3);
        cy.setMaterial(new PhongMaterial(Color.GREEN));

        Cylinder cx = new Cylinder(axis_rad * 4, 0, 128);
        cx.setTranslateX(AXIS_LENGTH / 2 - 0.3);
        cx.setMaterial(new PhongMaterial(Color.RED));

        Cylinder cz = new Cylinder(axis_rad * 4, 0, 128);
        cz.setTranslateZ(AXIS_LENGTH / 2 - 0.3);
        cz.setMaterial(new PhongMaterial(Color.BLUE));
        //endregion
        // setting rotation
        //region rotation
        xAxis.setTranslateZ(-50);
        yAxis.setTranslateX(-50);

        xAxisRotate.getChildren().add(xAxis);
        yAxisRotate.getChildren().add(yAxis);
        zAxisRotate.getChildren().add(zAxis);

        xAxisRotate.setTranslateZ(50.0);
        xAxisRotate.setRotationAxis(Rotate.X_AXIS);
        xAxisRotate.setRotate(90);

        yAxisRotate.setTranslateX(50.0);
        yAxisRotate.setRotationAxis(Rotate.Z_AXIS);
        yAxisRotate.setRotate(90);

        coneGroup.getChildren().get(1).setRotationAxis(Rotate.Z_AXIS);
        coneGroup.getChildren().get(1).setRotate(-90);

        coneGroup.getChildren().get(2).setRotationAxis(Rotate.X_AXIS);
        coneGroup.getChildren().get(2).setRotate(90);

        cx.setRotationAxis(Rotate.Z_AXIS);
        cx.setRotate(90);

        cz.setRotationAxis(Rotate.X_AXIS);
        cz.setRotate(90);
        //endregion

        // add everything to groups
        //region display
        axisGroup.getChildren().addAll(xAxisRotate, yAxisRotate, zAxisRotate);
        axisGroup.setVisible(true);
        coneGroup.getChildren().add(cy);
        coneGroup.getChildren().add(cx);
        coneGroup.getChildren().add(cz);
        world.getChildren().removeAll(coneGroup);
        world.getChildren().add(coneGroup);
        world.getChildren().addAll(axisGroup);
        root.getChildren().addAll(world);
        //endregion
    }

    /**
     * Author: Luca König
     * Function: creates a cone that fits on the axis
     * @param color
     */
    public void createCone(Color color) {
        // creates cones for axis
        PhongMaterial material = new PhongMaterial(color);

        float[] points = new float[rounds *12];
        float[] textCoords = {
                0.5f, 0,
                0, 1,
                1, 1
        };
        int[] faces = new int[rounds *12];

        // creating points
        for(int i= 0; i<rounds; i++){
            int index = i*12;
            // point 0
            points[index] = (float)Math.cos(Math.toRadians(i))*r2;
            points[index+1] = (float)Math.sin(Math.toRadians(i))*r2;
            points[index+2] = h/2;
            // point 1
            points[index+3] = (float)Math.cos(Math.toRadians(i))*r1;
            points[index+4] = (float)Math.sin(Math.toRadians(i))*r1;
            points[index+5] = -h/2;
            // point 2
            points[index+6] = (float)Math.cos(Math.toRadians(i+1))*r1;
            points[index+7] = (float)Math.sin(Math.toRadians(i+1))*r1;
            points[index+8] = -h/2;
            // point 3
            points[index+9] = (float)Math.cos(Math.toRadians(i+1))*r2;
            points[index+10] = (float)Math.sin(Math.toRadians(i+1))*r2;
            points[index+11] = h/2;
        }

        // set faces
        for(int i = 0; i<rounds ; i++){
            int index = i*12;
            faces[index]=i*4;
            faces[index+1]=0;
            faces[index+2]=i*4+1;
            faces[index+3]=1;
            faces[index+4]=i*4+2;
            faces[index+5]=2;

            faces[index+6]=i*4;
            faces[index+7]=0;
            faces[index+8]=i*4+2;
            faces[index+9]=1;
            faces[index+10]=i*4+3;
            faces[index+11]=2;
        }

        // drawing the cone
        TriangleMesh mesh = new TriangleMesh();
        mesh.getPoints().addAll(points);
        mesh.getTexCoords().addAll(textCoords);
        mesh.getFaces().addAll(faces);

        Cylinder circle1 = new Cylinder(r1, 0.1);
        circle1.setMaterial(material);
        circle1.setTranslateZ( -h / 2);
        circle1.setRotationAxis(Rotate.X_AXIS);
        circle1.setRotate(90);

        Cylinder circle2 = new Cylinder(r2, 0.1);
        circle2.setMaterial(material);
        circle2.setTranslateZ( h / 2);
        circle2.setRotationAxis(Rotate.X_AXIS);
        circle2.setRotate(90);

        Group cone = new Group();
        MeshView meshView = new MeshView();
        meshView.setMesh(mesh);
        meshView.setMaterial(material);
        cone.getChildren().addAll(meshView);
        Rotate r1 = new Rotate(90, Rotate.X_AXIS);
        cone.getTransforms().add(r1);
        coneGroup.getChildren().addAll(cone);
    }

    /**
     * Author: Luca König
     * Function: handles the mouseinput for the cameracontroll
     * of the 3D-Scene
     * @param scene
     */
    private void handleMouse(SubScene scene) {
        // control for the zoom
        //region Zoom
        scene.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent me) {
                mouseScroll = me.getDeltaY();
                //zoom-in
                if(mouseScroll > 0){
                    camera.setTranslateZ(camera.getTranslateZ()*0.8);
                }
                //zoom-out
                else if(mouseScroll < 0){
                    camera.setTranslateZ(camera.getTranslateZ()*1.2);
                }
            }
        });
        //endregion
        // camera movement
        //region Movement
        //get the current camera location
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        //move camera
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                // setting a modifier to adjust movement speed
                double modifier = 1.0;

                if (me.isControlDown()) {
                    modifier = CONTROL_MULTIPLIER;
                }
                if (me.isShiftDown()) {
                    modifier = SHIFT_MULTIPLIER;
                }

                // rotate scene by defining new rotation using mouse movement
                if (me.isPrimaryButtonDown()) {
                    cameraXform.rz.setAngle(cameraXform.rz.getAngle() - mouseDeltaX * 0.5 * modifier * ROTATION_SPEED);
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() + mouseDeltaY * 0.5 * modifier * ROTATION_SPEED);
                }

                // drag scene (fast) by setting new translate using mouse movement
                else if (me.isSecondaryButtonDown()) {
                    cameraXform2.t.setZ(cameraXform2.t.getZ() + mouseDeltaX * MOUSE_SPEED*2 * modifier * TRACK_SPEED);
                    cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY * MOUSE_SPEED*2 * modifier * TRACK_SPEED * -1);
                }

                // drag scene (slow) by setting new translate using mouse movement
                else if (me.isMiddleButtonDown()) {
                    cameraXform2.t.setZ(cameraXform2.t.getZ() + mouseDeltaX*MOUSE_SPEED * 1 * modifier * TRACK_SPEED);
                    cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY*MOUSE_SPEED * 1 * modifier *TRACK_SPEED * -1);
                }
            }
        });
        //endregion
    }

    /**
     * Author: Luca König
     * Function: action to reset te camera
     * @return
     */
    public Action reset(){
        cameraXform2.t.setX(0.0);
        cameraXform2.t.setY(0.0);
        cameraXform2.t.setZ(0.0);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rz.setAngle(CAMERA_INITIAL_Z_ANGLE);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        return null;
    }

    /**
     * Author: Luca König
     * Function: sets the view to straight from above
     * @return
     */
    public Action top_view(){
        cameraXform2.t.setX(0.0);
        cameraXform2.t.setY(0.0);
        cameraXform2.t.setZ(0.0);
        cameraXform.rx.setAngle(0);
        cameraXform.ry.setAngle(90);
        cameraXform.rz.setAngle(0);
        return null;
    }

    /**
     * Author: Luca König
     * Function: sets the view to a front-view
     * @return
     */
    public Action front_view(){
        cameraXform2.t.setX(0.0);
        cameraXform2.t.setY(0.0);
        cameraXform2.t.setZ(0.0);
        cameraXform.rx.setAngle(-90);
        cameraXform.ry.setAngle(0);
        cameraXform.rz.setAngle(90);
        return null;
    }

    /**
     * Author: Luca König
     * Function: sets to view to a side-view
     * @return
     */
    public Action side_view(){
        cameraXform2.t.setX(0.0);
        cameraXform2.t.setY(0.0);
        cameraXform2.t.setZ(0.0);
        cameraXform.rx.setAngle(-90);
        cameraXform.ry.setAngle(0);
        cameraXform.rz.setAngle(0);
        return null;
    }

    /**
     * Author: Luca König
     * Function: inverts the view (front <-> back, top <-> bottom, left <-> right)
     * @return
     */
    public Action flip_view(){
        cameraXform2.t.setX(0.0);
        cameraXform2.t.setY(0.0);
        cameraXform2.t.setZ(0.0);
        if(cameraXform.ry.getAngle() == 90 || cameraXform.ry.getAngle() == -90){
            cameraXform.ry.setAngle(cameraXform.ry.getAngle()*-1);
        }
        else if(cameraXform.rz.getAngle() == 0 && cameraXform.ry.getAngle() != 90 && cameraXform.ry.getAngle() != -90){
            cameraXform.ry.setAngle(cameraXform.ry.getAngle() + 180);
        }
        else if(cameraXform.ry.getAngle() == 180 && cameraXform.rx.getAngle() != 90 && cameraXform.rx.getAngle() != -90){
            cameraXform.ry.setAngle(cameraXform.ry.getAngle() - 180);
        }
        else if(cameraXform.rz.getAngle() == 90 || cameraXform.rz.getAngle() == -90)
            cameraXform.rz.setAngle(cameraXform.rz.getAngle() *-1);
        return null;
    }

    /**
     * Author: Luca König
     * Function: makes a step of 10 degrees around the x-axis
     * @return
     */
    public Action add_rotate(){
        cameraXform.rz.setAngle(cameraXform.rz.getAngle() + 10);
        return null;
    }

    /**
     * Author: Luca König
     * Function: makes a step of -10 degrees around the x-axis
     * @return
     */
    public Action sub_rotate(){
        cameraXform.rz.setAngle(cameraXform.rz.getAngle() - 10);
        return null;
    }

    /**
     * Author: Luca König
     * Function: tilts the camera with 10 degrees
     * @return
     */
    public Action add_tilt(){
        cameraXform.ry.setAngle(cameraXform.ry.getAngle() + 10);
        return null;
    }

    /**
     * Author: Luca König
     * Function: tilts the camera with -10 degrees
     * @return
     */
    public Action sub_tilt(){
        cameraXform.ry.setAngle(cameraXform.ry.getAngle() - 10);
        return null;
    }

    /**
     * Author: Luca König
     * Function: toggles the visibility of the axis
     * @return
     */
    public Action toggle_axis(){
        axisGroup.setVisible(!axisGroup.isVisible());
        coneGroup.setVisible(!coneGroup.isVisible());
        return null;
    }

    /**
     * Author: Luca König
     * Function: moves a line positive along waits internal z-axis
     * @param id
     * @return
     */
    public Action move_forward(int id){
        selected = figureGroup.getChildren().get(id);
        float[] vec = (float[]) figureGroup.getChildren().get(id).getUserData();
        selected.setTranslateX(selected.getTranslateX() + 0.05 * (vec[0] - vec[3]));
        selected.setTranslateY(selected.getTranslateY() + 0.05 * (vec[1] - vec[4]));
        selected.setTranslateZ(selected.getTranslateZ() + 0.05 * (vec[2] - vec[5]));
        return null;
    }

    /**
     * Author: Luca König
     * Function: moves a line negative along its internal z-axis
     * @param id
     * @return
     */
    public Action move_back(int id){
        selected = figureGroup.getChildren().get(id);
        float[] vec = (float[]) figureGroup.getChildren().get(id).getUserData();
        selected.setTranslateX(selected.getTranslateX() - 0.05 * (vec[0] - vec[3]));
        selected.setTranslateY(selected.getTranslateY() - 0.05 * (vec[1] - vec[4]));
        selected.setTranslateZ(selected.getTranslateZ() - 0.05 * (vec[2] - vec[5]));
        return null;
    }

    /**
     * Author: Luca König
     * Function: moves a pane along its internal y-axis in negative direction
     * @param id
     * @return
     */
    public Action move_neg_y(int id){
        float[] vec = (float[]) figureGroup.getChildren().get(id).getUserData();
        selected = figureGroup.getChildren().get(id);
        selected.setTranslateX(selected.getTranslateX() + 0.05 * (vec[0] - vec[6]));
        selected.setTranslateY(selected.getTranslateY() + 0.05 * (vec[1] - vec[7]));
        selected.setTranslateZ(selected.getTranslateZ() + 0.05 * (vec[2] - vec[8]));
        // moves the points
//        for(int i = verticGroup.getChildren().toArray().length - 1; i >= 0 ; i--){
//            if(verticGroup.getChildren().get(i).getId().equals(String.valueOf(id))){
//                verticGroup.getChildren().get(i).setTranslateX(verticGroup.getChildren().get(i).getTranslateX() + 0.05 * (vec[0] - vec[6]));
//                verticGroup.getChildren().get(i).setTranslateY(verticGroup.getChildren().get(i).getTranslateY() + 0.05 * (vec[1] - vec[7]));
//                verticGroup.getChildren().get(i).setTranslateZ(verticGroup.getChildren().get(i).getTranslateZ() + 0.05 * (vec[2] - vec[8]));
//            }
//        }
        return null;
    }

    /**
     * Author: Luca König
     * Function: moves a pane along its internal y-axis in positive direction
     * @param id
     * @return
     */
    public Action move_pos_y(int id){
        float[] vec = (float[]) figureGroup.getChildren().get(id).getUserData();
        selected = figureGroup.getChildren().get(id);
        selected.setTranslateX(selected.getTranslateX() - 0.05 * (vec[0] - vec[6]));
        selected.setTranslateY(selected.getTranslateY() - 0.05 * (vec[1] - vec[7]));
        selected.setTranslateZ(selected.getTranslateZ() - 0.05 * (vec[2] - vec[8]));
        // moves the points
//        for(int i = verticGroup.getChildren().toArray().length - 1; i >= 0 ; i--){
//            if(verticGroup.getChildren().get(i).getId().equals(String.valueOf(id))){
//                verticGroup.getChildren().get(i).setTranslateX(verticGroup.getChildren().get(i).getTranslateX() - 0.05 * (vec[0] - vec[6]));
//                verticGroup.getChildren().get(i).setTranslateY(verticGroup.getChildren().get(i).getTranslateY() - 0.05 * (vec[1] - vec[7]));
//                verticGroup.getChildren().get(i).setTranslateZ(verticGroup.getChildren().get(i).getTranslateZ() - 0.05 * (vec[2] - vec[8]));
//            }
//        }
        return null;
    }

    /**
     * Author: Luca König
     * Function: moves a pane along its internal x-axis in negative direction
     * @param id
     * @return
     */
    public Action move_neg_x(int id){
        float[] vec = (float[]) figureGroup.getChildren().get(id).getUserData();
        selected = figureGroup.getChildren().get(id);
        selected.setTranslateX(selected.getTranslateX() - 0.05 * (vec[0] - vec[3]));
        selected.setTranslateY(selected.getTranslateY() - 0.05 * (vec[1] - vec[4]));
        selected.setTranslateZ(selected.getTranslateZ() - 0.05 * (vec[2] - vec[5]));
        // moves the points
//        for(int i = verticGroup.getChildren().toArray().length - 1; i >= 0 ; i--){
//            if(verticGroup.getChildren().get(i).getId().equals(String.valueOf(id))){
//                verticGroup.getChildren().get(i).setTranslateX(verticGroup.getChildren().get(i).getTranslateX() - 0.05 * (vec[0] - vec[3]));
//                verticGroup.getChildren().get(i).setTranslateY(verticGroup.getChildren().get(i).getTranslateY() - 0.05 * (vec[1] - vec[4]));
//                verticGroup.getChildren().get(i).setTranslateZ(verticGroup.getChildren().get(i).getTranslateZ() - 0.05 * (vec[2] - vec[5]));
//            }
//        }
        return null;
    }

    /**
     * Author: Luca König
     * Function: moves a pane along its internal x-axis in positive direction
     * @param id
     * @return
     */
    public Action move_pos_x(int id){
        float[] vec = (float[]) figureGroup.getChildren().get(id).getUserData();
        selected = figureGroup.getChildren().get(id);
        selected.setTranslateX(selected.getTranslateX() + 0.05 * (vec[0] - vec[3]));
        selected.setTranslateY(selected.getTranslateY() + 0.05 * (vec[1] - vec[4]));
        selected.setTranslateZ(selected.getTranslateZ() + 0.05 * (vec[2] - vec[5]));
        // moves the points
//        for(int i = verticGroup.getChildren().toArray().length - 1; i >= 0 ; i--){
//            if(verticGroup.getChildren().get(i).getId().equals(String.valueOf(id))){
//                verticGroup.getChildren().get(i).setTranslateX(verticGroup.getChildren().get(i).getTranslateX() + 0.05 * (vec[0] - vec[3]));
//                verticGroup.getChildren().get(i).setTranslateY(verticGroup.getChildren().get(i).getTranslateY() + 0.05 * (vec[1] - vec[4]));
//                verticGroup.getChildren().get(i).setTranslateZ(verticGroup.getChildren().get(i).getTranslateZ() + 0.05 * (vec[2] - vec[5]));
//            }
//        }
        return null;
    }

    /**
     * Author: Luca König
     * Function: creates a line with an id between two given points
     * @param id
     * @param originF
     * @param targetF
     * @param color
     */
    public  void createLine(int id, float[] originF, float[] targetF, Color color) {
        // array for translate values
        float[] vec = new float[6];
        vec[0] = originF[0];
        vec[1] = originF[1];
        vec[2] = originF[2];
        vec[3] = targetF[0];
        vec[4] = targetF[1];
        vec[5] = targetF[2];

        // creating origin and target point
        Point3D origin = new Point3D(originF[0],originF[1],originF[2]);
        Point3D target = new Point3D(targetF[0],targetF[1],targetF[2]);

        // draw line
        double rad = 0.05;
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D diff = target.subtract(origin);
        double height = diff.magnitude();

        Point3D mid = target.midpoint(origin);
        Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());

        // rotate line to match origin point
        Point3D axisOfRotation = diff.crossProduct(yAxis);
        double angle = Math.acos(diff.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);

        Cylinder line = new Cylinder(rad, height, 16);

        line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);
        line.setMaterial(new PhongMaterial(color));
        line.setUserData(vec);

        world.getChildren().removeAll(figureGroup);
        if(id == -1){
            figureGroup.getChildren().add(line);
        }
        else if(id >= 0){
            figureGroup.getChildren().add(id,line);
        }
        world.getChildren().addAll(figureGroup);
    }

//    /**
//     * Author: Luca König
//     * Function: first try for the planes,
//     * creates a parallelogram from three given points
//     * @param r
//     * @param a
//     * @param b
//     * @param color
//     */
//    public void createPlane(float[] r, float[] a, float[] b, Color color){
//        TriangleMesh paneMesh = new TriangleMesh();
//        paneMesh.getTexCoords().addAll(0,0);
//
//        float x0 = r[0];
//        float y0 = r[1];
//        float z0 = r[2];
//
//        float x1 = a[0];
//        float y1 = a[1];
//        float z1 = a[2];
//
//        float x2 = b[0];
//        float y2 = b[1];
//        float z2 = b[2];
//
//        float x3 = (x2 - x0) + x1;
//        float y3 = (y2 - y0) + y1;
//        float z3 = (z2 - z0) + z1;
//
//        // Print coords
////        System.out.println(x0 + "|" + y0 + "|" + z0);
////        System.out.println(x1 + "|" + y1 + "|" + z1);
////        System.out.println(x2 + "|" + y2 + "|" + z2);
////        System.out.println(x3 + "|" + y3 + "|" + z3);
//
//        // creates a pane (parallelogram)
//        paneMesh.getPoints().addAll(
//                x0 ,y0, z0,         // p0
//                x1, y1, z1,         // p1
//                x2, y2, z2,         // p2
//                x3, y3, z3          // p3
//        );
//
//        paneMesh.getFaces().addAll(
//                3,0, 2,0, 1,0,      // face 1 up
//                1,0, 2,0, 3,0,      // face 1 down
//                1,0, 2,0, 0,0,      // face 2 up
//                0,0, 2,0, 1,0       // face 2 down
//        );
//
//        createSphere(x0,y0,z0, Color.BLUE);
//        createSphere(x1,y1,z1, Color.GREEN);
//        createSphere(x2,y2,z2, Color.GREEN);
//        createMiddle(x0, y0, z0, x1, y1, z1, x2, y2, z2);
//
//        MeshView pane = new MeshView(paneMesh);
//        pane.setDrawMode(DrawMode.FILL);
//        pane.setMaterial(new PhongMaterial(Color.ORANGE));
//        pane.setTranslateX(0);
//        pane.setTranslateY(0);
//        pane.setTranslateZ(0);
//
//        world.getChildren().remove(figureGroup);
//        figureGroup.getChildren().add(pane);
//        world.getChildren().add(figureGroup);
//    }

    /**
     * Author: Luca König
     * Function: calculates a center point between the three given
     * @param x0    \
     * @param y0     >  Point 0
     * @param z0    /
     * @param x1    \
     * @param y1     >  Point 1
     * @param z1    /
     * @param x2    \
     * @param y2     >  Point 2
     * @param z2    /
     * @return
     */
    private float[] createMiddle(int id, float x0, float y0, float z0, float x1, float y1, float z1, float x2, float y2, float z2){
        float midx = ((x0) + (x1) + (x2)) / 3;
        float midy = ((y0) + (y1) + (y2)) / 3;
        float midz = ((z0) + (z1) + (z2)) / 3;

        float[] res = {midx, midy, midz};

        createSphere(id, midx, midy, midz, Color.CYAN);

        return res;
    }

    /**
     * Author: Luca König
     * Function: creates a plane from the center point, with three given points in it
     * @param id
     * @param vr
     * @param va
     * @param vb
     * @param a
     * @param b
     * @param c
     * @param d
     * @param color
     * @param n
     */
    public void createPlane(int id, float[] vr, float[] va, float[] vb, float a, float b, float c, float d, Color color, boolean n , float mult){
        //region Variables
        float[] vec = new float[9];

        // new Point 0
        float x0, y0, z0;

        // new Point 1
        float x1, y1, z1;

        // new Point 2
        float x2, y2, z2;

        // new Point 3
        float x3, y3, z3;

        // Point 0
        float ox0 = vr[0];
        float oy0 = vr[1];
        float oz0 = vr[2];

        // Point 1
        float ox1 = va[0];
        float oy1 = va[1];
        float oz1 = va[2];

        // Point 2
        float ox2 = vb[0];
        float oy2 = vb[1];
        float oz2 = vb[2];

        // setting the color
        createSphere(id, vr[0],vr[1],vr[2], Color.GREEN);
        createSphere(id, va[0],va[1],va[2], Color.GREEN);
        createSphere(id, vb[0],vb[1],vb[2], Color.GREEN);

        // center Point
        float[] mid = createMiddle(id, ox0, oy0, oz0, ox1, oy1, oz1, ox2, oy2, oz2);
        float midx = mid[0];
        float midy = mid[1];
        float midz = mid[2];
        vec[0] = mid[0];
        vec[1] = mid[1];
        vec[2] = mid[2];
        //endregion
        // calculating the new vertices (edge points)
        //region calculation
        float px0 = midx + mult;
        float py0 = midy;
        float pz0;

        float px1 = midx;
        float py1 = midy + mult;
        float pz1;

        float px2 = midx - mult;
        float py2 = midy;
        float pz2;

        float px3 = midx;
        float py3 = midy - mult;
        float pz3;

        // calculate z-coordinates if c != null
        if(c != 0){
            pz0 = ((a*px0) + (b*py0) - (d))/(-c);
            pz1 = ((a*px1) + (b*py1) - (d))/(-c);
            pz2 = ((a*px2) + (b*py2) - (d))/(-c);
            pz3 = ((a*px3) + (b*py3) - (d))/(-c);

            // P0
            x0 = px0 + (px1 - midx);
            y0 = py0 + (py1 - midy);
            z0 = pz0 + (pz1 - midz);

            vec[6] = px0;
            vec[7] = py0;
            vec[8] = pz0;

            // P1
            x1 = px0 + (midx - px1);
            y1 = py0 + (midy - py1);
            z1 = pz0 + (midz - pz1);
            vec[3] = px1;
            vec[4] = py1;
            vec[5] = pz1;

            // P2
            x2 = px2 + (px3 - midx);
            y2 = py2 + (py3 - midy);
            z2 = pz2 + (pz3 - midz);

            // P3
            x3 = px2 + (midx - px3);
            y3 = py2 + (midy - py3);
            z3 = pz2 + (midz - pz3);

//            // draws new outline-points
//            createSphere(id, x0,y0,z0,Color.RED);
//            createSphere(id, x1,y1,z1,Color.DARKRED);
//            createSphere(id, x2,y2,z2,Color.BLUE);
//            createSphere(id, x3,y3,z3,Color.DARKBLUE);
        }
        // if c is null z-coordinates don't change
        else{
            pz0 = midz;
            pz1 = midz;
            pz2 = midz;
            pz3 = midz;

            // P0
            x0 = px0 + (px1 - midx);
            y0 = py0 + (py1 - midy);
            z0 = pz0 + (pz1 - midz) - mult;
            vec[3] = px0;
            vec[4] = y0;
            vec[5] = pz0;


            // P1
            x1 = px0 + (px1 - midx);
            y1 = py0 + (py1 - midy);
            z1 = pz0 + (pz1 - midz) + mult;
            vec[6] = px1;
            vec[7] = midy;
            vec[8] = z1;


            // P2
            x2 = px2 + (px3 - midx);
            y2 = py2 + (py3 - midy);
            z2 = pz2 + (pz3 - midz) + mult;


            // P3
            x3 = px2 + (px3 - midx);
            y3 = py2 + (py3 - midy);
            z3 = pz2 + (pz3 - midz) - mult;

//            // draws new outline-points
//            createSphere(id, x0, y0,z0,Color.RED);
//            createSphere(id, x1,y1,z1,Color.DARKRED);
//            createSphere(id, x2,y2,z2,Color.BLUE);
//            createSphere(id, x3,y3,z3,Color.DARKBLUE);

        }


        //endregion

        //region draw new Pane
        TriangleMesh paneMesh = new TriangleMesh();
        paneMesh.getTexCoords().addAll(0,0);

        // set points used for faces
        paneMesh.getPoints().addAll(
                x0,y0,z0,               // p0
                x1,y1,z1,               // p1
                x2,y2,z2,               // p2
                x3,y3,z3                // p3
        );

        // set faces using points
        paneMesh.getFaces().addAll(
                3,0, 2,0, 1,0,
                1,0, 2,0, 3,0,
                3,0, 1,0, 0,0,
                0,0, 1,0, 3,0
        );

        // adding figure to groups and drawing plane
        MeshView pane = new MeshView(paneMesh);
        pane.setDrawMode(DrawMode.FILL);
        pane.setMaterial(new PhongMaterial(color));
        pane.setTranslateX(0);
        pane.setTranslateY(0);
        pane.setTranslateZ(0);
        pane.setUserData(vec);

        world.getChildren().remove(figureGroup);
        if (n){
            figureGroup.getChildren().add(pane);
        }
        else if(n == false){
            figureGroup.getChildren().add(id, pane);
        }
        world.getChildren().add(figureGroup);
    }

//    public void createPlane_2(int id, float[] vr, float[] va, float[] vb, float a, float b, float c, float d, Color color, boolean n){
//        createSphere(id, vr[0], vr[1], vr[2], Color.RED);
//        createSphere(id, va[0], va[1], va[2], Color.AQUAMARINE);
//        createSphere(id, vb[0], vb[1], vb[2], Color.BEIGE);
//
//        float mid[] = createMiddle(id, vr[0], vr[1], vr[2], va[0], va[1], va[2], vb[0], vb[1], vb[2]);
//        createSphere(id, mid[0], mid[1], mid[2], Color.YELLOW);
//
//        float x0;
//        float y0;
//        float z0;
//
//        System.out.println("a: " + a);
//        System.out.println("b: " + b);
//        System.out.println("c: " + c);
//        System.out.println("d: " + d);
//
//        x0 = mid[0] + 5;
//        y0 = mid[1];
//
//        //moved center point
//        if(c != 0) {
//            z0 = a*x0 + b*y0 - d;
//            System.out.println("z0_1: " + z0);
//            z0 = z0 / -c;
//            System.out.println("1. c: " + c);
//        }
//        else{
//            z0 = mid[2];
//            System.out.println("2. c: " + c);
//        }
//        System.out.println("x0: " + x0);
//        System.out.println("y0: " + y0);
//        System.out.println("z0: " + z0);
//        createSphere(id, x0, y0, z0, Color.GREEN);
//
//        System.out.println("va: " + va[0] + "|" + va[1] + "|" + va[2] + "|");
//        System.out.println("vb: " + vb[0] + "|" + vb[1] + "|" + vb[2] + "|");
//
//
//
//        TriangleMesh paneMesh = new TriangleMesh();
//        paneMesh.getTexCoords().addAll(0,0);
//
//        paneMesh.getPoints().addAll(
//                vr[0], vr[1], vr[2],
//                va[0], va[1], va[2],
//                vb[0], vb[1], vb[2]
//        );
//
//        paneMesh.getFaces().addAll(
//                2,0, 1,0, 0,0,
//                0,0, 1,0, 2,0
//        );
//
//        MeshView pane = new MeshView(paneMesh);
//        pane.setDrawMode(DrawMode.FILL);
//        pane.setMaterial(new PhongMaterial(color));
//        pane.setTranslateX(0);
//        pane.setTranslateY(0);
//        pane.setTranslateZ(0);
//
//        world.getChildren().remove(figureGroup);
//        if (n == true){
//            figureGroup.getChildren().add(pane);
//            panes.add(id);
//        }
//        else{
//            figureGroup.getChildren().add(id, pane);
//        }
//        world.getChildren().add(figureGroup);
//
////        ax + by + cz     = d		    |-cz
////        ax + by 	       = d - cz	    |- d
////        ax + by - d      = -cz		|/-c
////        (ax + by - d)/-c = z
//
//    }

//    /**
//     * Author: Luca König
//     * Function: creates a label for a given point/ sphere
//     * !!! doesn't work --> label is to big !!!
//     * @param id
//     * @param fx
//     * @param fy
//     * @param fz
//     * @param node
//     */
//
//    private void createLabel(int id, float fx, float fy, float fz, Node node){
//        Label label= new Label();
//        double x = (double)fx;
//        double y = (double)fy;
//        double z = (double)fz;
//
//        label.setId(String.valueOf(id));
//
//        label.setScaleX(0.1);
//        label.setScaleY(0.1);
//        label.setScaleZ(0.1);
//        label.setRotate(180);
//        label.setLabelFor(node);
//
//
//        label.setText("X: " + x + "\nY: " + y + "\nZ: " + z);
//
//
//        labelGroup.getChildren().add(label);
//        world.getChildren().remove(labelGroup);
//        world.getChildren().add(labelGroup);
//    }

    /**
     * Author: Luca König
     * Function: creates a sphere at a specific point to
     * visualize points in the 3D-Scene
     * @param x
     * @param y
     * @param z
     * @param c
     */
    private void createSphere(int id, float x, float y, float z, Color c){
        Sphere vertic = new Sphere(0.2);
        vertic.setMaterial(new PhongMaterial(c));
        vertic.setTranslateX(x);
        vertic.setTranslateY(y);
        vertic.setTranslateZ(z);
        vertic.setId(String.valueOf(id));
        //createLabel(id, x, y, z, vertic);

        world.getChildren().remove(verticGroup);
        verticGroup.getChildren().add(vertic);
        world.getChildren().add(verticGroup);
    }

    /**
     * Author: Luca König
     * Function: deletes a specific plane by using the given id
     * @param id
     */
    public void deletePlane(int id){
        figureGroup.getChildren().remove(id);

        // delete vertices
        for(int i = verticGroup.getChildren().toArray().length - 1; i >= 0 ; i--){
            if(verticGroup.getChildren().get(i).getId().equals(String.valueOf(id))){
                verticGroup.getChildren().remove(i);
            }
        }

        // correcting the ID
        for(int i = verticGroup.getChildren().toArray().length - 1; i >= 0 ; i--){
            if(Integer.parseInt(verticGroup.getChildren().get(i).getId()) > id){
                verticGroup.getChildren().get(i).setId(String.valueOf(Integer.parseInt(verticGroup.getChildren().get(i).getId()) - 1));
            }
        }
    }

    /**
     * Author Luca König
     * Function: deletes a specific line by using the given id
     * @param id
     */
    public void deleteLine(int id){
        figureGroup.getChildren().remove(id);
    }

    /**
     * Author: Luca König
     * Function: alternative function to edit existing planes
     * @param id
     * @param vr
     * @param va
     * @param vb
     * @param a
     * @param b
     * @param c
     * @param d
     * @param color
     */
    public void editPlane(int id, float[] vr, float[] va, float[] vb, float a, float b, float c, float d, Color color, float mult){
        figureGroup.getChildren().remove(id);
        createPlane(id, vr, va, vb, a, b, c, d, color, false, mult);
    }

    /**
     * Author: Luca König
     * Function: alternative function to edit existing lines
     * @param id
     * @param vr
     * @param va
     * @param vb
     * @param color
     */
    public void editLine(int id, float[] vr, float[] va, float[] vb, Color color){
        figureGroup.getChildren().remove(id);
        createLine(id, vr, va, color);
    }

    /**
     * Author: Luca König
     * Function: edits the line/ plane with the given id
     * or transforms it to each other
     * @param id
     * @param vr
     * @param va
     * @param vb
     * @param a
     * @param b
     * @param c
     * @param d
     * @param color
     * @param plane
     */
    public void editFigure(int id, float[] vr, float[]va, float[] vb, float a, float b, float c, float d, Color color, boolean plane, float mult){
        // save the current translation
        selected = figureGroup.getChildren().get(id);
        double translateX = selected.getTranslateX();
        double translateY = selected.getTranslateY();
        double translateZ = selected.getTranslateZ();

        // remove old figures
        figureGroup.getChildren().remove(id);
        for(int i = verticGroup.getChildren().toArray().length - 1; i >= 0 ; i--){
            if(verticGroup.getChildren().get(i).getId().equals(String.valueOf(id))){
                verticGroup.getChildren().remove(i);
            }
        }

        // create new figures and translate them to match old position
        if(plane){
            createPlane(id, vr, va, vb, a, b, c, d, color, false, mult);
            selected = figureGroup.getChildren().get(id);
            selected.setTranslateX(translateX);
            selected.setTranslateY(translateY);
            selected.setTranslateZ(translateZ);
        }
        else if(!plane){
            createLine(id, vr, va, color);
            selected = figureGroup.getChildren().get(id);
            selected.setTranslateX(translateX);
            selected.setTranslateY(translateY);
            selected.setTranslateZ(translateZ);
        }
    }

    /**
     * Author: Luca König
     * Function: toggles the visibility of
     * the selected object/ figure
     * @param id
     * @param plane
     */
    public void toggleVisibility(int id, boolean plane){
        if(plane == true) {
            for(int i = verticGroup.getChildren().toArray().length - 1; i >= 0 ; i--){
                if(verticGroup.getChildren().get(i).getId().equals(String.valueOf(id))){
                    verticGroup.getChildren().get(i).setVisible(!verticGroup.getChildren().get(i).isVisible());
                }
            }
            figureGroup.getChildren().get(id).setVisible(!figureGroup.getChildren().get(id).isVisible());
        }
        else {
            if (figureGroup.getChildren().get(id).isVisible()) {
                figureGroup.getChildren().get(id).setVisible(false);
            } else {
                figureGroup.getChildren().get(id).setVisible(true);
            }
        }
    }

    /**
     * Author: Luca König
     * Function: creates the Visualization as subscene
     * @return
     */
    public SubScene start() {

        buildCamera();
        buildAxis();

        SubScene scene = new SubScene(root, 500, 500, true, SceneAntialiasing.BALANCED);
        handleMouse(scene);
        scene.setCamera(camera);

        return scene;
    }
}