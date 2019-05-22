import React, { Component } from 'react'
import Axios from 'axios';
import url from '../../config/config';

export default class HackathonEarningReport extends Component {

    constructor(props) {
        super(props);
        this.state = {
            hackathonList: [],
            selectedHackathonId: "",
            hackathonMetrics : [],
            selectedackathonName : "",
            expense : 0,
        }
    }

    componentDidMount() {
        Axios.get(url + "/hackathons")
            .then((response) => {
                console.log(response);
                this.setState({
                    hackathonList: response.data,
                })
            })
            .catch((error) => {
                console.log(error);
                alert("Error");
            })
    }

    handleHackathonResults = (e, id, name) => {
        e.preventDefault();
        Axios.get(url + "/getMetrics/" + id)
        .then((response) => {
            console.log(response.data);
            this.setState({
                hackathonMetrics : response.data,
                selectedHackathonId : id,
                selectedackathonName : name,
            })
        })
        .catch((error) => {
            console.log(error);
            alert("Error");
        })
    }

    render() {

        let {hackathonList, selectedHackathonId, hackathonMetrics} = this.state;

        let resultsList = hackathonList.map((item) => {
            return (
                <div className="row">
                    <li class="list-group-item col-sm-8 mr-2" >{item.name}</li>
                    <button className="btn btn-dark col-sm-3" onClick={(e) => this.handleHackathonResults(e, item.id, item.name)}>view results</button>
                </div>
            )
        })

        let selectedHackathonDiv = (
            <div>
                <h3>{this.state.selectedackathonName}</h3>
                <br/>
                Revenue : {hackathonMetrics[0]}<br/>
                Sponsors : {hackathonMetrics[1] * 1000}<br/>
                Expense : {this.state.expense} <br/>
                Profit : {hackathonMetrics[0] + (hackathonMetrics[1] * 1000)}<br/>
            </div>
        )

        return (
            <div className="container mt-5">
                <h1 style={{ color: "black" }}>Hackathon Earning Report</h1>
                <div className="row formContainer">
                    <ul className="col-sm-6 list-group">{resultsList}</ul>
                    <ul className="col-sm-6 list-group">
                        {/* {this.state.selectedHackathonId ?
                            <div className="row" style={{ marginBottom: "-30px" }}>
                                <label className="col-sm-6">
                                    Team Name
                            </label>
                                <label className="col-sm-2 mt-2 ml-5">Payment Status</label>
                            </div> : <div></div>
                        } */}
                        {selectedHackathonId ? selectedHackathonDiv : <div className="col-sm-6"></div>}
                    </ul>
                </div>
            </div>
        )
    }
}
