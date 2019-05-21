import React, { Component } from 'react';
import Axios from 'axios';
import url from '../../config/config';


class Judge extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userid: localStorage.getItem("userid"),
            HackathonsList: [],
            urls: ["localhost1", "localhost2", "localhost3"],
            points: [2, 3, 4]
        }
    }

    componentDidMount = () => {
        //get all hackathons

        Axios.get(url + "/hackathons")
            .then((response) => {
                console.log("all hackathons", response.data);
                var hackathonsWithThisJudge_temp = []
                for (let item of response.data) {
                    if (item.judgeList.includes(this.state.userid)) {
                        hackathonsWithThisJudge_temp.push(item)
                    }
                }
                this.setState({
                    hackathonsWithThisJudge: hackathonsWithThisJudge_temp
                })
            })
            .catch((err) => {
                if (err.response) {
                    alert(err.response.data);
                }
            })


        //get all teams from all those hackathons

        Axios.get(url + "/hackathonsTeams")
            .then((response) => {
                console.log("all hackathons teams", response.data);
            })
            .catch((err) => {
                if (err.response) {
                    alert(err.response.data);
                }
            })

    }

    judge = (e) => {
        e.preventDefault();
    }
    render() {
        var urlList = "";
        urlList = this.state.urls.map((item, i) => {
            return (
                <div className="row justify-content-start">
                    <label className="col-sm-4">Hackathon 1</label>
                    <label className="col-sm-4">{item}</label>
                    <input className="form-control col-sm-1" type="text" name="price" value={this.state.points[i]}></input>
                    <button className="btn btn_login ml-4 col-sm-1" onClick={this.gradeTeam}>Save</button>
                </div>
            )
        })

        return (
            <div className="mt-5">
                <div className="formContainer" style={{ marginLeft: "10%", marginRight: "10%" }}>
                    <h3>Judge Hackathon</h3>
                    <center>
                        <div className="row justify-content-start">
                            <h4 className="col-sm-4">Hackathon</h4>
                            <h4 className="col-sm-4">Submission URL</h4>
                            <h4 className="col-sm-1">Score</h4>
                        </div>
                        {urlList}
                        <div className="row justify-content-center">
                            <button className="btn btn_login form_element" name="judge" onClick={this.judge} >DONE</button>
                        </div>
                    </center>
                </div>
            </div>
        );
    }
}

export default Judge;