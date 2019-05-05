import React, { Component } from 'react';

class JoinHackathon extends Component {
    constructor(props) {
        super(props);
        this.state = {
            teamSize: 5
        }
    }
    render() {

        var teamMembers = Array.apply(null, { length: this.state.teamSize }).map((e, i) => (
            <tr>
                <td><input type="text" name={"member" + (i + 1)} placeholder={"team member " + (i + 1)} ></input></td>
                <td>
                    <select class="form-control" id="role">
                        <option>ProductManger</option>
                        <option>Engineer</option>
                        <option>Full Stack</option>
                        <option>Designer</option>
                        <option>Other</option>
                    </select>
                </td>
            </tr>
        ));


        return (
            <div>
                <center>
                    <h3>Enter Team Details</h3>
                    <table>
                        <tr>
                            <td><input type="text" name="teamName" placeholder="Team Name" />
                            </td>
                            <td>
                            </td>
                        </tr>
                        {teamMembers}
                    </table>
                    <div>
                        <button>Join Hackathon</button>
                    </div>
                </center>
            </div>
        );
    }
}

export default JoinHackathon;