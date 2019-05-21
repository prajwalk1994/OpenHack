import React, { Component } from 'react'
import Axios from 'axios';
import url from '../../config/config';
import { Modal, Button } from 'react-bootstrap';

export default class FinancialReport extends Component {

    constructor(props) {
        super(props);

        this.state = {
            hackathonList: [],
            selectedHackathonTeams: [],
            selectedHackathonId: "",
            selectedTeamId: "",
            selectedTeamMembers: [],
            show: false,
        }
        // this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
    }

    handleClose() {
        this.setState({ show: false });
    }



    componentDidMount() {
        Axios.get(url + '/hackathons')
            .then((response) => {
                console.log(response.data)
                this.setState({
                    hackathonList: response.data,
                })
            })
            .catch((error) => {
                console.log(error);
                alert("Error")
            })
    }

    handleHackathonResults = (e, id) => {
        e.preventDefault();
        Axios.get(url + '/teamsByHackathon/' + id)
            .then((response) => {
                console.log(response.data);
                this.setState({
                    selectedHackathonTeams: response.data,
                    selectedHackathonId: id,
                })
            })
            .catch((error) => {
                console.log(error);
                alert("Error");
            })
    }

    handleTeamMembers = (e, teamId) => {
        e.preventDefault();
        Axios.get(url + "/getTeamMembers/" + teamId)
            .then(async (response) => {
                console.log(response.data);
                await this.setState({
                    selectedTeamMembers: response.data,
                    selectedTeamId: teamId,
                })
            })
            .catch((error) => {
                console.log(error);
                alert("Error");
            })

        this.setState({ show: true });
    }

    render() {
        console.log(this.state.selectedTeamMembers)
        var teamPaymentDetailsDiv = "";

        teamPaymentDetailsDiv = this.state.selectedTeamMembers.map((item) => {
            console.log(item)

            return (
                <div className="row">
                    <li className="list-group-item col-sm-6">
                        {item.user.username}
                    </li>
                    <li className="list-group-item col-sm-6">
                        {item.amount}
                    </li>
                </div>
            )
        })

        let resultsList = this.state.hackathonList.map((item) => {
            return (
                <div className="row">
                    <li class="list-group-item col-sm-8 mr-2" >{item.name}</li>
                    <button className="btn btn-dark col-sm-3" onClick={(e) => this.handleHackathonResults(e, item.id)}>view results</button>
                </div>
            )
        })

        let selectedHackathonDiv = this.state.selectedHackathonTeams.map((member) => {
            console.log(member.teamId.teamName + " " + member.payments);
            return (
                <div className="row">
                    <div className="col-sm-4">
                        {member.teamId.teamName}
                    </div>
                    <div className="col-sm-4">
                        {member.payments ? "PAID" : "NOT PAID"}
                    </div>
                    <div className="col-sm-4">
                        <button className="btn btn-primary" onClick={(e) => this.handleTeamMembers(e, member.teamId.id)}>View Details</button>
                    </div>
                </div>
            )
        })

        return (
            <div className="container mt-5">
                <h1 style={{ color: "black" }}>Hackathon Financial Report</h1>
                <div className="row formContainer">
                    <ul className="col-sm-6 list-group">{resultsList}</ul>
                    <ul className="col-sm-6 list-group">
                        {this.state.selectedHackathonId ? 
                        <div className="row" style={{ marginBottom: "-30px" }}>
                            <label className="col-sm-6">
                                Team Name
                            </label>
                            <label className="col-sm-2 mt-2 ml-5">Payment Status</label>
                        </div> : <div></div>
                        }
                        {this.state.selectedHackathonId ? selectedHackathonDiv : <div className="col-sm-6"></div>}
                    </ul>
                </div>
                <Modal show={this.state.show} onHide={this.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Team Details</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <ul className="list-group">
                            {teamPaymentDetailsDiv}
                        </ul>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.handleClose}>
                            Close
                        </Button>
                    </Modal.Footer>
                </Modal>
            </div>
        )
    }
}
