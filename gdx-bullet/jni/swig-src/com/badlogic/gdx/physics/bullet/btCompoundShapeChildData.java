/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.5
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet;

public class btCompoundShapeChildData {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected btCompoundShapeChildData (long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	public static long getCPtr (btCompoundShapeChildData obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize () {
		delete();
	}

	public synchronized void delete () {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				gdxBulletJNI.delete_btCompoundShapeChildData(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setM_transform (btTransformFloatData value) {
		gdxBulletJNI.btCompoundShapeChildData_m_transform_set(swigCPtr, this, btTransformFloatData.getCPtr(value), value);
	}

	public btTransformFloatData getM_transform () {
		long cPtr = gdxBulletJNI.btCompoundShapeChildData_m_transform_get(swigCPtr, this);
		return (cPtr == 0) ? null : new btTransformFloatData(cPtr, false);
	}

	public void setM_childShape (btCollisionShapeData value) {
		gdxBulletJNI.btCompoundShapeChildData_m_childShape_set(swigCPtr, this, btCollisionShapeData.getCPtr(value), value);
	}

	public btCollisionShapeData getM_childShape () {
		long cPtr = gdxBulletJNI.btCompoundShapeChildData_m_childShape_get(swigCPtr, this);
		return (cPtr == 0) ? null : new btCollisionShapeData(cPtr, false);
	}

	public void setM_childShapeType (int value) {
		gdxBulletJNI.btCompoundShapeChildData_m_childShapeType_set(swigCPtr, this, value);
	}

	public int getM_childShapeType () {
		return gdxBulletJNI.btCompoundShapeChildData_m_childShapeType_get(swigCPtr, this);
	}

	public void setM_childMargin (float value) {
		gdxBulletJNI.btCompoundShapeChildData_m_childMargin_set(swigCPtr, this, value);
	}

	public float getM_childMargin () {
		return gdxBulletJNI.btCompoundShapeChildData_m_childMargin_get(swigCPtr, this);
	}

	public btCompoundShapeChildData () {
		this(gdxBulletJNI.new_btCompoundShapeChildData(), true);
	}

}