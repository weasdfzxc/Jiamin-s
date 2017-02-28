using UnityEngine;
using System.Collections;

public class CacheManager : MonoBehaviour {
    public GameObject plane;
    private GameObject tmpPlane;
    private bool ok = true;
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
        if (GameObject.Find("Vector DrawnLine") != null&&ok)
        {
            plane = GameObject.Find("Vector DrawnLine");
            tmpPlane = new GameObject("tempPlane");
            tmpPlane.AddComponent<MeshFilter>();
            tmpPlane = plane;
            ok = false;
        }  
	}
}