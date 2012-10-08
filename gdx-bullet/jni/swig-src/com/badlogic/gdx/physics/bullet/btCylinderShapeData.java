/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.5
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet;

public class btCylinderShapeData {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected btCylinderShapeData (long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	public static long getCPtr (btCylinderShapeData obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize () {
		delete();
	}

	public synchronized void delete () {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				gdxBulletJNI.delete_btCylinderShapeData(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setM_convexInternalShapeData (btConvexInternalShapeData value) {
		gdxBulletJNI.btCylinderShapeData_m_convexInternalShapeData_set(swigCPtr, this, btConvexInternalShapeData.getCPtr(value),
			value);
	}

	public btConvexInternalShapeData getM_convexInternalShapeData () {
		long cPtr = gdxBulletJNI.btCylinderShapeData_m_convexInternalShapeData_get(swigCPtr, this);
		return (cPtr == 0) ? null : new btConvexInternalShapeData(cPtr, false);
	}

	public void setM_upAxis (int value) {
		gdxBulletJNI.btCylinderShapeData_m_upAxis_set(swigCPtr, this, value);
	}

	public int getM_upAxis () {
		return gdxBulletJNI.btCylinderShapeData_m_upAxis_get(swigCPtr, this);
	}

	public void setM_padding (String value) {
		gdxBulletJNI.btCylinderShapeData_m_padding_set(swigCPtr, this, value);
	}

	public String getM_padding () {
		return gdxBulletJNI.btCylinderShapeData_m_padding_get(swigCPtr, this);
	}

	public btCylinderShapeData () {
		this(gdxBulletJNI.new_btCylinderShapeData(), true);
	}

}
