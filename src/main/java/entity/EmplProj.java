package entity;

import java.util.Objects;

public class EmplProj extends Entity {
    private Long empId;
    private Long projId;

    public EmplProj() {
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getProjId() {
        return projId;
    }

    public void setProjId(Long projId) {
        this.projId = projId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmplProj emplProj = (EmplProj) o;
        return Objects.equals(empId, emplProj.empId) &&
                Objects.equals(projId, emplProj.projId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, projId);
    }

    @Override
    public String toString() {
        return "EmplProjService{" +
                "empId=" + empId +
                ", projId=" + projId +
                '}';
    }
}
