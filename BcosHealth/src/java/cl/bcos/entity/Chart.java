/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.entity;

/**
 *
 * @author aacantero
 */
public class Chart {
    
    private String title;
    private String descripcion;
    private String labelsPoints;
    private String descriptionPoints;
    private String dataPoints;
    private Float maxValue;
    private Float minValue;
    private String id;

    
    
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
 
    public String getTitle() {
        return title;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLabelsPoints() {
        return labelsPoints;
    }

    public String getDescriptionPoints() {
        return descriptionPoints;
    }

    public String getDataPoints() {
        return dataPoints;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLabelsPoints(String labelsPoints) {
        this.labelsPoints = labelsPoints;
    }

    public void setDescriptionPoints(String descriptionPoints) {
        this.descriptionPoints = descriptionPoints;
    }

    public void setDataPoints(String dataPoints) {
        this.dataPoints = dataPoints;
    }

    public void setMaxValue(Float maxValue) {
        this.maxValue = maxValue;
    }

    public void setMinValue(Float minValue) {
        this.minValue = minValue;
    }

    public Float getMaxValue() {
        return maxValue;
    }

    public Float getMinValue() {
        return minValue;
    }

    
    
    
}
