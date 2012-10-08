/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.5
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet;

public class btQuantizedBvhNodeData {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected btQuantizedBvhNodeData (long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	public static long getCPtr (btQuantizedBvhNodeData obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize () {
		delete();
	}

	public synchronized void delete () {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				gdxBulletJNI.delete_btQuantizedBvhNodeData(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setM_quantizedAabbMin (int[] value) {
		gdxBulletJNI.btQuantizedBvhNodeData_m_quantizedAabbMin_set(swigCPtr, this, value);
	}

	public int[] getM_quantizedAabbMin () {
		return gdxBulletJNI.btQuantizedBvhNodeData_m_quantizedAabbMin_get(swigCPtr, this);
	}

	public void setM_quantizedAabbMax (int[] value) {
		gdxBulletJNI.btQuantizedBvhNodeData_m_quantizedAabbMax_set(swigCPtr, this, value);
	}

	public int[] getM_quantizedAabbMax () {
		return gdxBulletJNI.btQuantizedBvhNodeData_m_quantizedAabbMax_get(swigCPtr, this);
	}

	public void setM_escapeIndexOrTriangleIndex (int value) {
		gdxBulletJNI.btQuantizedBvhNodeData_m_escapeIndexOrTriangleIndex_set(swigCPtr, this, value);
	}

	public int getM_escapeIndexOrTriangleIndex () {
		return gdxBulletJNI.btQuantizedBvhNodeData_m_escapeIndexOrTriangleIndex_get(swigCPtr, this);
	}

	public btQuantizedBvhNodeData () {
		this(gdxBulletJNI.new_btQuantizedBvhNodeData(), true);
	}

}
