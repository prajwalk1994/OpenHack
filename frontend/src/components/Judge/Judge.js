import React, { Component } from 'react';
import Axios from 'axios';
import url from '../../config/config';


class Judge extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userid: localStorage.getItem("userid"),
            email: localStorage.getItem("email"),
            HackathonsList: [],
            urls: ["localhost1", "localhost2", "localhost3"],
            points: [2, 3, 4],
            hackathonsWithThisJudge: [],
            finalTeams: [],
            finalGrades: [],
            judgeScore: 0,
        }
    }

    componentDidMount = async () => {
        //get all hackathons
        await Axios.get(url + "/hackathons")
            .then(async (response) => {
                console.log("all hackathons", response.data);
                var hackathonsWithThisJudge_temp = []
                for (let item of response.data) {
                    for (let j of item.judgeList) {
                        if (j.email === this.state.email) {
                            hackathonsWithThisJudge_temp.push(item)
                        }
                    }
                }
                await this.setState({
                    hackathonsWithThisJudge: hackathonsWithThisJudge_temp
                })
            })
            .catch((err) => {
                if (err.response) {
                    alert(err.response.data);
                }
            })

        console.log("filtered hackathons for this judge", this.state.hackathonsWithThisJudge)


        //get all teams from all those hackathons

        await Axios.get(url + "/hackathonTeams")
            .then(async (response) => {
                console.log("all hackathons teams", response.data);
                var tempTeams = []
                var tempObj = {}
                for (let i of response.data) {
                    for (let j of this.state.hackathonsWithThisJudge) {
                        if (i.hackId.id == j.id && i.submissionUrl) {
                            tempObj["hackId"] = i.hackId.id
                            tempObj["hackathonName"] = i.hackId.name;
                            tempObj["teamName"] = i.teamId.name;
                            tempObj["submissionUrl"] = i.submissionUrl
                            tempObj["teamId"] = i.teamId.id
                            tempObj["score"] = i.teamId.score
                            tempTeams.push(tempObj)
                        }
                    }
                }
                await this.setState({
                    finalTeams: tempTeams
                })
            })
            .catch((err) => {
                if (err.response) {
                    alert(err.response.data);
                }
            })
        console.log(this.state.finalTeams)

    }

    judge = (e) => {
        e.preventDefault();
    }

    gradeTeam = async (e, i) => {
        e.preventDefault();
        console.log(this.state)
        await Axios.post(url + "/gradeHackathon/" + this.state.finalTeams[i].hackId + "/" + this.state.finalTeams[i].teamId + "/" + this.state.judgeScore)
            .then((response) => {
                console.log("response", response.data)
                alert("Graded successfully");
            })
            .catch((err) => {
                console.log("err", err)
            })
        console.log(this.state)
    }



    handleChange = (e, i) => {

        this.setState({
            judgeScore: e.target.value,
        })
    }

    render() {
        var urlList = "";
        urlList = this.state.finalTeams.map((item, i) => {
            return (
                <div className="row justify-content-start">
                    <label className="col-sm-3">{item.hackathonName}</label>
                    <label className="col-sm-3">{item.teamName}</label>
                    <label className="col-sm-3">{item.submissionUrl}</label>
                    <input className="form-control col-sm-1" type="text" name="judgeScore" onChange={(e) => this.handleChange(e)} ></input>
                    <button className="btn btn_login ml-4 col-sm-1" onClick={(e) => this.gradeTeam(e, i)}>Save</button>
                </div>
            )
        })

        return (
            <div className="mt-5">
                <div className="formContainer" style={{ marginLeft: "10%", marginRight: "10%" }}>
                    <h3>Judge Hackathon</h3>
                    <center>
                        <div className="row justify-content-start">
                            <h4 className="col-sm-3">Hackathon</h4>
                            <h4 className="col-sm-3">Team</h4>
                            <h4 className="col-sm-3">Submission URL</h4>
                            <h4 className="col-sm-1">Score</h4>
                        </div>
                        {urlList}
                    </center>
                </div>
            </div>
        );
    }
}

export default Judge;